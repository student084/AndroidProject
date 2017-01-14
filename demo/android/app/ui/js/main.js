/*
******************************************************************************
*Copyright(c).
*Allrightsreserved.
*
*File $Filename:main.js
*Author$Author:student0$
*Version$Revision:0.0.1$
*Date$Date:2016/12/21 20:36$
*Description
  $appName: demo
  user control main page
******************************************************************************
*/
import React, { Component } from 'react';

import {
	View,
	Text,
	Button,
	Navigator,
	DrawerLayoutAndroid,
	StyleSheet,
	TextInput,
	Image,
	TouchableHighlight,
	TouchableOpacity,
}from 'react-native';
import Drawer from './drawer';

export default class MainPage extends React.Component {
	constructor(props){
		super(props);
		this.state = {
			drawer: null,
			userName: "",
		};
	}

	componentDidMount(){
		//get params' values
		this.setState({
			userName: this.props.userName,
		});
	}
	render(){
		return(
		<DrawerLayoutAndroid
	      drawerWidth={200}
	      drawerPosition={DrawerLayoutAndroid.positions.Left}
	      ref={(drawer) => { this.drawer = drawer; }}
	      renderNavigationView={this._drawer}>
	    	<View style={renderContentCss.maxLayout}>
			<View style={renderContentCss.headWithSearch}>
			 <TouchableOpacity onPress={this._showDrawerMenu.bind(this)}>
				<View style={renderContentCss.picLayout}><Image style={renderContentCss.userImg} source={require('../img/student0.png')}/></View>
				</TouchableOpacity>
				<View style={renderContentCss.searchInputView}><TextInput/></View>
				<TouchableOpacity><View style={renderContentCss.searchButtonView}><Image style={renderContentCss.userImg} source={require('../img/menu.png')}/></View></TouchableOpacity>
			</View>
			<View style={renderContentCss.center}>
				<Text> DEMO </Text>
				<Text> An React Native Simple Demo </Text>
			</View>
			<View style={renderContentCss.contentLayout}></View>
			</View>
			<View style={renderContentCss.tableMenu}>
			<TouchableHighlight><View style={mainCss.tableChoose}><View><Image style={mainCss.tableImg} source={require('../img/recommend.png')}/></View><Text>推荐</Text></View></TouchableHighlight>
			<TouchableHighlight><View style={mainCss.tableChoose}><View><Image style={mainCss.tableImg} source={require('../img/friend.png')}/></View><Text>聊天</Text></View></TouchableHighlight>
			<TouchableHighlight><View style={mainCss.tableChoose}><View><Image style={mainCss.tableImg} source={require('../img/history.png')}/></View><Text>记录</Text></View></TouchableHighlight>
			<TouchableHighlight><View style={mainCss.tableChoose}><View><Image style={mainCss.tableImg} source={require('../img/acount.png')}/></View><Text>管理</Text></View></TouchableHighlight>
			</View>
	    </DrawerLayoutAndroid>
			);
	}
	_showDrawerMenu = () => {
		this.drawer.openDrawer();
	}
	//return the Drawer's view by a function
	_drawer = () =>{
		return(<LeftListMenu/>);
	}
}

class LeftListMenu extends Component{
	render(){
		return(
		<View style={{flex: 1,  alignItems: 'flex-start'}}>
			<View style={{margin: 15, flex: 1, alignItems: 'flex-start'}}>
			<Image style={mainCss.userImg} 
                    source={require('../img/student0.png')}
                    />
			</View>
			<View style={{flex: 4, alignItems: 'flex-start'}}>
          		<Text style={{margin: 10, fontSize: 15, textAlign: 'right'}}>选项一</Text>
          		<Text style={{margin: 10, fontSize: 15, textAlign: 'right'}}>选项二</Text>
          		<Text style={{margin: 10, fontSize: 15, textAlign: 'right'}}>选项三</Text>
          		<Text style={{margin: 10, fontSize: 15, textAlign: 'right'}}>选项四</Text>
			</View>
		</View>
		);
	}
}

// class RenderContent extends Component{
// 	render(){
// 		return();
// 	}
// }

//get window size
let Dimensions=require('Dimensions');
let ScreenWidth=Dimensions.get('window').width;
let ScreenHeight=Dimensions.get('window').height;
let ScreenScale=Dimensions.get('window').scale;
renderContentCss = StyleSheet.create({
	maxLayout:{
		height: ScreenHeight * 0.87,
		width: ScreenWidth,
		flexDirection: 'column',
		justifyContent: 'flex-start',
		alignItems: 'center',
	},
	picLayout:{
		height: ScreenHeight * 0.1,
		width: ScreenWidth * 0.2,
		justifyContent: 'space-around',
		alignItems: 'center',
		backgroundColor: 'gainsboro',
	},
	searchInputView:{
		height: ScreenHeight * 0.08,
		width: ScreenWidth * 0.45,
		backgroundColor: 'gainsboro',
	},
	searchButtonView:{
		height: ScreenHeight * 0.1,
		width: ScreenWidth * 0.25,
		justifyContent: 'center',
		alignItems: 'center',
		backgroundColor: 'gainsboro',
	},
	headWithSearch:{
		height: ScreenHeight * 0.1,
		width: ScreenWidth,
		flexDirection: 'row',
		justifyContent: 'space-between',
		alignItems: 'center',
		backgroundColor: 'gainsboro',
	},
	center:{
		width: ScreenWidth,
		height: ScreenHeight * 0.1,
		backgroundColor: 'gainsboro',
		justifyContent: 'space-around',
		alignItems: 'center',
	},
	contentLayout:{
		height: 0.67 * ScreenHeight,
		width: ScreenWidth,
	},
	tableMenu:{
		paddingTop: 5, 
		width: ScreenWidth,
		height: ScreenHeight * 0.13,
		backgroundColor: 'lavender',
		flexDirection: 'row',
		justifyContent: 'flex-start',
		borderStyle: 'solid',
  		borderWidth: 1,
  		borderLeftColor: 'lavender',
  		borderRightColor: 'lavender',
	},
	userImg:{
		borderRadius: ScreenHeight * 0.04,
	    height:ScreenHeight * 0.08,
	    width:ScreenHeight * 0.08,  
	    alignSelf:'center',
	},
});

mainCss = StyleSheet.create({
	userImg:{
    borderRadius:40,  
    height:80,  
    width:80,  
    marginTop:40,  
    alignSelf:'center',
  },
  tableChoose:{
  	width: ScreenWidth * 0.25,
  	height: ScreenHeight * 0.1,
  	justifyContent: 'flex-start',
  	alignItems: 'center',
  },
  tableImg:{
	height: 25,
	width: 25,  
	alignSelf:'center',
  },
});

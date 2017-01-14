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
				<View style={renderContentCss.searchButtonView}><Button title="Search" onPress={this._showDrawerMenu.bind(this)}/></View>
			</View>
			<View style={renderContentCss.center}></View>
			<View style={renderContentCss.contentLayout}></View>
			</View>
			<View style={renderContentCss.tableMenu}></View>
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
	},
	searchInputView:{
		height: ScreenHeight * 0.08,
		width: ScreenWidth * 0.45,
	},
	searchButtonView:{
		height: ScreenHeight * 0.1,
		width: ScreenWidth * 0.25,
		justifyContent: 'center',
		alignItems: 'center',
	},
	headWithSearch:{
		height: ScreenHeight * 0.1,
		width: ScreenWidth,
		flexDirection: 'row',
		justifyContent: 'space-between',
		alignItems: 'center',
	},
	center:{
		width: ScreenWidth,
		height: ScreenHeight * 0.1,
		backgroundColor: 'red',
	},
	contentLayout:{
		height: 0.67 * ScreenHeight,
		width: ScreenWidth,
	},
	tableMenu:{
		width: ScreenWidth,
		height: ScreenHeight * 0.13,
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
});

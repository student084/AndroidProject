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
	Navigator,
	DrawerLayoutAndroid,
	StyleSheet,
	Image,
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
	    	<View style={{flex: 1}}>
	    	<View>
	      	<RenderContent/>
	      	</View>
	      	<View>
	      	<Text onPress={this._showDrawerMenu.bind(this)}>Press to Menu</Text>
	      	</View>
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

class RenderContent extends Component{
	render(){
		return(<View><Text>this is drawer</Text></View>);
	}
}

renderContentCss = StyleSheet.create({

});

mainCss = StyleSheet.create({
	userImg:{
    borderRadius:40,  
    height:80,  
    width:80,  
    marginTop:40,  
    alignSelf:'center',
  }
});

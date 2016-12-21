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
}from 'react-native';
import Drawer from './drawer';

export default class MainPage extends React.Component {
	constructor(props){
		super(props);
		this.state = {
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
		let navigationView = (
		<View style={{flex: 1, alignItems: 'center'}}>
          <Text style={{margin: 10, fontSize: 15, textAlign: 'right'}}>Frist list</Text>
          <Text style={{margin: 10, fontSize: 15, textAlign: 'right'}}>Second list</Text>
          <Text style={{margin: 10, fontSize: 15, textAlign: 'right'}}>Thrid list</Text>
          <Text style={{margin: 10, fontSize: 15, textAlign: 'right'}}>Fouth list</Text>
        </View>
          );
		return(
		<DrawerLayoutAndroid
	      drawerWidth={300}
	      drawerPosition={DrawerLayoutAndroid.positions.Left}
	      renderNavigationView={() => navigationView}>
	    	<View style={{flex: 1, backgroundColor: '#fff'}}>
	      	<Text style={{margin: 10, fontSize: 15, textAlign: 'left'}}>Welcom to Drawer!</Text>
	    	</View>
	    </DrawerLayoutAndroid>
			);
	}

	_showDrawer = () =>{
		const {navigator} = this.props;
		if(navigator){
			navigator.push({
				component: Drawer,
			});
		}
	}
}
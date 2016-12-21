/*
******************************************************************************
*Copyright(c).
*Allrightsreserved.
*
*File $Filename:drawer.js
*Author$Author:student0$
*Version$Revision:0.0.1$
*Date$Date:2016/12/21 20:36$
*Description
  $appName: demo
  
******************************************************************************
*/
import React, { Component } from 'react';

import {
	View,
	Text,
	Navigator,
	DrawerLayoutAndroid,
}from 'react-native';

export default class Drawer extends React.Component {
	constructor(props){
		super(props);
		this.state = {
		};
	}

	componentDidMount(){
		//get params' values
		this.setState({

					});
	}

	render(){
		//the view below used to  contain element when drawer's display is true 
		var navigationView = (
	    <View>
	    </View>
  );
	return (
		////the view below be used to  contain element when drawer's display is false
	    <DrawerLayoutAndroid
	      drawerWidth={300}
	      drawerPosition={DrawerLayoutAndroid.positions.Left}
	      renderNavigationView={() => navigationView}>
	      <View>
	      </View>
	    </DrawerLayoutAndroid>
  );
}
}
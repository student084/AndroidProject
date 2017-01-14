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
	StyleSheet,
	Image,
	TouchableHighlight,
	TouchableOpacity,
	ListView,
}from 'react-native';
import Drawer from './drawer';

export default class Recommend extends React.Component {
	constructor(props){
		super(props);
		var ds = new ListView.DataSource({rowHasChanged: (r1, r2) => r1 !== r2});
		this.state = {
			dataSource: ds.cloneWithRows(['data1', 'data2', 'data3', 'data4', 'data5']),
		};
	}

	componentDidMount(){
		//get params' values
		this.setState({
		});
	}
	// the attribute of dataSource get the data
	//the attribute of renderRow send the data to the face and item control how the face look like 
	render(){
		return(
			<TouchableOpacity>
			<View style={commCss.pageLayout}>
				<ListView
				contentContainerStyle={recommendCss.listViewStyle}
				showsVerticalScrollIndicator={true}
				dataSource={this.state.dataSource}
				renderRow={this._renderRow}
				/>
			</View>
			</TouchableOpacity>
			);
	}
	_renderRow(rowData){
		return (
			<TouchableOpacity>
			<View style={recommendCss.myItem}><Text>{rowData}</Text></View>
			</TouchableOpacity>
			);
	}
}

commCss = StyleSheet.create({
	pageLayout:{
		flex: 1,
	},
});

recommendCss = StyleSheet.create({
	myItem:{
		height: 50,
	},
	listViewStyle:{

	}
});
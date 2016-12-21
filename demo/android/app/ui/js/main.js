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
	      drawerWidth={260}
	      drawerPosition={DrawerLayoutAndroid.positions.Left}
	      renderNavigationView={this._showDrawer}>
	    	<View style={{flex: 1}}>
	      	<RenderContent/>
	    	</View>
	    </DrawerLayoutAndroid>
			);
	}

	_showDrawer = () =>{
		return(<LeftListMenu/>);
	}
}

class LeftListMenu extends Component{
	render(){
		return(
		<View style={{flex: 1,  alignItems: 'flex-start'}}>
			<View style={{margin: 20,flex: 1, alignItems: 'flex-start'}}>
			<Image style={mainCss.userImg} 
                    source={require('../img/student0.png')}
                    />
			</View>
			<View style={{flex: 3,  alignItems: 'flex-start'}}>
          		<Text style={{margin: 50, fontSize: 15, textAlign: 'right'}}></Text>
          		<Text style={{margin: 50, fontSize: 15, textAlign: 'right'}}>Second list</Text>
          		<Text style={{margin: 50, fontSize: 15, textAlign: 'right'}}>Thrid list</Text>
          		<Text style={{margin: 50, fontSize: 15, textAlign: 'right'}}>Fouth list</Text>
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

mainCss = StyleSheet.create({
	userImg:{
    borderRadius:40,  
    height:80,  
    width:80,  
    marginTop:40,  
    alignSelf:'center',
  }
});

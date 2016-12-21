
/*
******************************************************************************
*Copyright(c).
*Allrightsreserved.
*
*File $Filename:index.js
*Author$Author:student0$
*Version$Revision:0.0.1$
*Date$Date:2016/12/21 20:36$
*Description
  $appName: demo
  set Navigator and set path to JS file
******************************************************************************
*/
import React, { Component } from 'react';
import {
  Navigator,
  Text,
  View
} from 'react-native';

import Login from './js/login';

export default class Index extends React.Component {
    render() {
    let defaultComponent = Login;
    return (
    <Navigator
        initialRoute = {{component: defaultComponent}}
        configureScene={(route)=>{
        return Navigator.SceneConfigs.VerticalDownSwipeJump;
        }
        }
        renderScene={(route, navigator) => {
            let Component = route.component;
            return <Component{...route.params} navigator={navigator}/>
        }}/>
    );
  }
}
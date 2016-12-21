
import React, { Component } from 'react';
import {
  Navigator,
  StyleSheet,
  Text,
  View
} from 'react-native';

import Login from './js/login';

export default class Index extends Component {
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
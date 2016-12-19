/*
 ******************************************************************************
 * Copyright (c).
 * All rights reserved.
 *
 * File		$Filename: MySceneTwo.js
 * Author	$Author: student0 $
 * Version	$Revision: 0.0.1 $
 * Date		$Date: 2016/12/18 15:44 $
 * Description
******************************************************************************
*/

import React, { Component, PropTypes} from 'react';
import {
        StyleSheet,
         View,
         Text,
         Navigator
          } from 'react-native';
import NavigationBar from './NavigationBar'
export default class MySceneTwo extends React.Component{
        constructor(props) {
                super(props);
                this.state = {
                msg: null,
                };
            }
        componentDidMount(){
            this.setState({
                msg: this.props.msg,
            });
        }
   _backHome(){
         const { navigator } = this.props;
                if(navigator) {
                    //很熟悉吧，入栈出栈~ 把当前的页面pop掉，这里就返回到了上一个页面:FirstPageComponent了
                    navigator.pop();
                }
   }
        render(){
            return(
             <View style={styles2.container}>
                   <Text style={styles2.welcome} >
                        Welcome to RN2!
                   </Text>
                   <Text style={styles2.instructions} onPress={this._backHome.bind(this)}>
                        To get started, touch to back.
                   </Text>
                   <Text style={styles2.instructions} onPress={this._backHome.bind(this)}>
                         {this.state.msg}
                    </Text>
                   <Text style={styles2.instructions}>
                        Double tap R on your keyboard to reload,{'\n'}
                   Shake or press menu button for dev menu
                   </Text>
             </View>
            );
        }
}

const styles2 = StyleSheet.create({
  container: {
    flex: 1,
//    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  instructions: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5,
  },
});
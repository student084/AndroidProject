/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */
/*
******************************************************************************
*Copyright(c).
*Allrightsreserved.
*
*File       $Filename: userInformation.js
*Author     $Author:student0$
*Version    $Revision:0.0.1$
*Date       $Date:2016/12/19 16:15$
*Description
    $ProjectName:RNshow
******************************************************************************
*/
import React, { Component } from 'react';
import {
  StyleSheet,
  Text,
  View,
  TouchableHighlight,
} from 'react-native';
// the view for showing the information of users, which has been input at indexPage
export default class UserInformation extends React.Component{
    constructor(props){
        super(props);
        this.state = {
            userName: "",
            passWord: "",
        };
        }
     // Get data after mount
     componentDidMount(){
        this.setState({
            userName: this.props.userName,
            passWord: this.props.passWord,
        });
     }
     _back = () => {
        const { navigator } = this.props;
        if(navigator){
            navigator.pop();
        }
     }
    render(){
        return (
        //container
        <View>
        <TouchableHighlight onPress={this._back.bind(this)}><Text> touch me to back</Text></TouchableHighlight>
        <View><Text>用户名：{ JSON.stringify(this.state.userName) }</Text></View>
        <View><Text>用户密码：{ JSON.stringify(this.state.passWord) }</Text></View>
        </View>
        );
    }
}
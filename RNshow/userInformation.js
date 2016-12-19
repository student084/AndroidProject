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
} from 'react-native';
// the view for showing the information of users, which has been input at indexPage
export default class UserInforShow extends React.Component{
    constructor(props){
        super(props);
        this.state = {
            userName: null,
            passWord: null,
        };
        }
     // Get data after mount
     componentDidMount(){
        this.setState({
            userName: this.props.userName,
            passWord: this.props.passWord,
        });
     }
    render(){
        return (
        //container
        <View>
        <View><Text>用户名：</Text></View>
        <View><Text>用户密码：</Text></View>
        <View>
        );
    }
}
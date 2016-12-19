/*
 ******************************************************************************
 * Copyright (c).
 * All rights reserved.
 *
 * File		$Filename: NavigationBar.js
 * Author	$Author: student0 $
 * Version	$Revision: 0.0.1 $
 * Date		$Date: 2016/12/17 21:33 $
 * Description
******************************************************************************
*/

import React, { Component, PropTypes} from 'react';
import {StyleSheet, View, Text} from 'react-native';

import MySceneOne from './index.android';
import MySceneTwo from './MySceneTwo';
export default class NavigationBar extends Component{
    constructor(props) {
            super(props);
            this.state = {
            };
        }
    _ToPageOne(){
        if(navigator){
            const { navigator } = this.props;
            navigator.push({
                component: MySceneOne,
                params:{
                         msg: 'hahaha give one',
                 }
            })
        }
    }
   _ToPageTwo(){
        if(navigator){
            const {navigator} = this.props;
            navigator.push({
                component: MySceneTwo,
               params:{
                    msg: 'hahaha give two',
               }
            })
        }
   }
    render(){
        return (
        <View style={style.barLayout}>
            <View style={[style.powderBlue, style.barPart]}><Text onPress={this._ToPageOne.bind(this)}>点击☞至页1</Text></View>
            <View style={[style.skyBlue, style.barPart]}><Text onPress={this._ToPageTwo.bind(this)}>点击☞页2</Text></View>
        </View>
        );
    }
}
var Dimensions = require('Dimensions');
var ScreenWidth = Dimensions.get('window').width;
var ScreenHeight = Dimensions.get('window').height;
var ScreenScale = Dimensions.get('window').scale;
style = StyleSheet.create({
    barPart: {
        height: 50,
        width: ScreenWidth/2,
        alignItems: 'center',
        justifyContent: 'center',
    },
    barLayout:{
        flexDirection: 'row',
        justifyContent: 'space-between',
    },
    powderBlue:{
        backgroundColor:'powderblue',
    },
    skyBlue:{
        backgroundColor:'skyblue',
    },
    steelBlue:{
        backgroundColor: 'steelblue',
    },
});

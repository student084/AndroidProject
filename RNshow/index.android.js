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
*File       $Filename:.index.android.js
*Author     $Author:student0$
*Version    $Revision:0.0.1$
*Date       $Date:2016/12/19 14:15$
*Description
    $ProjectName:RNshow
******************************************************************************
*/
import React, { Component } from 'react';
import {
  AppRegistry,
  StyleSheet,
  Text,
  View,
  TextInput,
  Button,
  Alert,
  Navigator
} from 'react-native';

import UserInformation from './UserInformation';
const submit = () =>{
    Alert.alert('App is building ...');
};
// Home page view
class RNshow extends Component {
  render() {
    let defaultComponent = HomeView;
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
        }
        }
    />
    );
  }
}

export default class HomeView extends Component{
    constructor(props){
        super(props);
        this.state = {
            userName : "",
            passWord : "",
        }
    }
    render(){
        return(
            //container
                <View style={homeStyles.container}>
                 <View style={homeStyles.headPicBox}>
                    <View></View>
                 </View>
                 <View style={homeStyles.inputsBox}>
                    <View><TextInput onChangeText={
                        (text) => {
                        this.state.userName = text;
                        }
                    }/></View>
                    <View><TextInput onChangeText={
                        (text) => {
                        this.state.passWord = text;
                         }
                    }/></View>
                 </View>
                 <View style={homeStyles.buttonsBox}>
                    <View>
                    <Button
                    title="登录"
                     onPress={this._onPressSubmit.bind(this)}
                     /></View>
                 </View>
                 <View style={homeStyles.linksBox}>
                    <View><Text>忘记密码？</Text></View>
                    <View><Text>新用户注册</Text></View>
                 </View>
                 <View style={homeStyles.bottomBox}>
                    <View><Text>student0</Text></View>
                 </View>
                 </View>
        );
    }
    _onPressSubmit = () => {
        this._loginSuccess();
    }

    _loginSuccess(){
        const { navigator } = this.props;
        if (navigator) {
           navigator.push({
            component: UserInformation,
            params: {
                userName: this.state.userName,
                passWord: this.state.passWord,
            }
           });
        }
    }
}
//get window size
var Dimensions=require('Dimensions');
var ScreenWidth=Dimensions.get('window').width;
var ScreenHeight=Dimensions.get('window').height;
var ScreenScale=Dimensions.get('window').scale;
homeStyles = StyleSheet.create({
  container: {
    flex: 1,
    //主轴为竖直
    flexDirection: 'column',
    //从主轴开始部位开始布局
    justifyContent: 'flex-start',
    //布局整体内部中对齐
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
  headPicBox:{
    height: 3 * ScreenHeight/10,
    width:  ScreenWidth,
    backgroundColor: '#fff0f5',
  },
  inputsBox:{
    height: 2 * ScreenHeight/10,
    width: ScreenWidth,
    backgroundColor: '#add8e6',
  },
  buttonsBox:{
    height: ScreenHeight/10,
    width:  ScreenWidth,
    flexDirection: 'column',
    justifyContent: 'center',
    backgroundColor: '#e0ffff',
  },
  linksBox:{
    height: ScreenHeight/10,
    width:  ScreenWidth,
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    backgroundColor: '#fafad2',
  },
  bottomBox:{
    height: 3 * ScreenHeight/10,
    width:  ScreenWidth,
    justifyContent: 'flex-end',
    backgroundColor: '#b0c4de',
  },
   inputTxt:{

   }
});

AppRegistry.registerComponent('RNshow', () => RNshow);

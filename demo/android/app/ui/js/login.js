import React, { Component } from 'react';
import {
  StyleSheet,
  Text,
  View,
  TextInput,
  Button,
} from 'react-native';

export default class Login extends Component{
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
                <View style={loginStyles.container}>
                 <View style={loginStyles.headPicBox}>
                    <View></View>
                 </View>
                 <View style={loginStyles.inputsBox}>
                    <View
                    ><TextInput onChangeText={
                        (text) => {
                        this.state.userName = text;
                        }
                      }
                         style={loginStyles.inputBox}/>
                         </View>
                    <View
                    ><TextInput onChangeText={
                        (text) => {
                        this.state.passWord = text;
                         }
                       }
                      maxLength={16}
                      secureTextEntry={true}
                      style={[loginStyles.inputBox, loginStyles.passWordInputBox]}
                       /></View>
                 </View>
                 <View style={loginStyles.buttonsBox}>
                    <View>
                    <Button
                    title="登录"
                     onPress={this._onPressSubmit.bind(this)}
                     /></View>
                 </View>
                 <View style={loginStyles.linksBox}>
                    <View><Text style={loginStyles.linkWord}>忘记密码？</Text></View>
                    <View><Text>新用户注册</Text></View>
                 </View>
                 <View style={loginStyles.bottomBox}>
                    <View><Text>student0</Text></View>
                 </View>
                 </View>
        );
    }
    _onPressSubmit = () => {
        this._loginSuccess();
    }

    _loginSuccess = () => {
        // const { navigator } = this.props;
        // if (navigator) {
        //    navigator.push({
        //     component: UserInformation,
        //     params: {
        //         userName: this.state.userName,
        //         passWord: this.state.passWord,
        //     }
        //    });
        // }
    }
}
//get window size
var Dimensions=require('Dimensions');
var ScreenWidth=Dimensions.get('window').width;
var ScreenHeight=Dimensions.get('window').height;
var ScreenScale=Dimensions.get('window').scale;
loginStyles = StyleSheet.create({
  container: {
    flex: 1,
    //主轴为竖直
    flexDirection: 'column',
    //从主轴开始部位开始布局
    justifyContent: 'flex-start',
    //布局整体内部中对齐
    alignItems: 'center',
    backgroundColor: 'lavender',
  },
  headPicBox:{
    height: 26 * ScreenHeight/90,
    width:  ScreenWidth,
    backgroundColor: 'lavender',
  },
  inputsBox:{
    height: 14 * ScreenHeight/90,
    width: ScreenWidth,
    backgroundColor: '#add8e6',
  },
  buttonsBox:{
    height: 11 * ScreenHeight/90,
    width:  495 * ScreenWidth/ 540,
    flexDirection: 'column',
    justifyContent: 'center',
    backgroundColor: 'dodgerblue',
  },
  linksBox:{
    height: 3 * ScreenHeight/90,
    width:  ScreenWidth,
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    backgroundColor: 'lavender',
  },
  bottomBox:{
    height: 36 * ScreenHeight/90,
    width:  ScreenWidth,
    justifyContent: 'flex-end',
    backgroundColor: 'lavender',
  },
  inputBox:{
    borderWidth: 0,
  },
  passWordInputBox:{
    
  },
  linkWord:{
    color: 'dodgerblue',
  }
});
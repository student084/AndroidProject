import React, { Component } from 'react';
import {
  StyleSheet,
  Text,
  View,
  TextInput,
  Button,
  Image,
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
                    <Image style={loginStyles.userImg} 
                    source={require('../img/student0.png')}
                    />
                 </View>
                 <View style={loginStyles.inputsBox}>
                    <View>
                    <TextInput onChangeText={
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
                      style={loginStyles.btn}
                     /></View>
                 </View>
                 <View style={loginStyles.linksBox}>
                    <View><Text style={loginStyles.linkWord}>忘记密码？</Text></View>
                    <View><Text style={loginStyles.linkWord}>新用户注册</Text></View>
                 </View>
                 <View style={loginStyles.bottomBox}>
                    <Text>Copyright © 2016 student0</Text>
                    <Text>All rights reserved</Text>
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
    backgroundColor: 'aliceblue',
  },
  buttonsBox:{
    height: 11 * ScreenHeight/90,
    width:  495 * ScreenWidth/ 540,
    flexDirection: 'column',
    justifyContent: 'center',
  },
  btn:{
     borderRadius: 20,
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
    height: 30 * ScreenHeight/90,
    width:  ScreenWidth,
    flexDirection: 'column',
    justifyContent: 'flex-end',
    alignItems: 'center',
    backgroundColor: 'lavender',
  },
  inputBox:{
    borderBottomColor: 'aliceblue',
    borderWidth: 0,
    borderBottomWidth: 0,
  },
  passWordInputBox:{
    
  },
  linkWord:{
    color: 'dodgerblue',
  },
  userImg:{
    borderRadius:40,  
    height:80,  
    width:80,  
    marginTop:40,  
    alignSelf:'center',
  }
});
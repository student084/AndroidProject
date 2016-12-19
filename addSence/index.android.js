/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, { Component } from 'react';
import {
  AppRegistry,
  StyleSheet,
  Navigator,
  Text,
  TouchableHighlight,
  View
} from 'react-native';
import NavigationBar from './NavigationBar'

class addScene extends Component {
  render() {
    let defaultTitle = 'Home';
    let defaultComponent = NavigationBar;
    return(
  <Navigator
               initialRoute={{ title: defaultTitle, component: defaultComponent }}
               configureScene={(route) => {
                 return Navigator.SceneConfigs.VerticalDownSwipeJump;
               }}
               renderScene={(route, navigator) => {
                 let Component = route.component;
                 return <Component {...route.params} navigator={navigator} />
  }} />
    );
  }
}
export default class MySceneOne extends Component{
  constructor(props) {
          super(props);
          this.state = {
            msg: null,
          };
      }
      componentDidMount(){
        //
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
        <View style={styles.container}>
            <Text style={styles.welcome} >
                Welcome to RN1!
            </Text>
            <Text style={styles.instructions} onPress={this._backHome.bind(this)}>
                To get started, touch to back.
            </Text>
            <Text style={styles.instructions}>
                Double tap R on your keyboard to reload,{'\n'}
                Shake or press menu button for dev menu
             </Text>
        </View>
        );
    }
}
//class MyScene extends Component{
//    static defaultProps = {
//        title: 'PageThree'
//    };
//    render(){
//        return(
//        <View style={styles.container}>
//        <TouchableHighlight onPress={this.props.onBack}>
//        <Text>BACK</Text>
//        </TouchableHighlight>
//            <Text style={styles.welcome}>
//                Welcome to RN PageThree!
//            </Text>
//            <Text style={styles.instructions}>
//                To get started, edit index.android.js
//            </Text>
//            <Text style={styles.instructions}>
//                Double tap R on your keyboard to reload,{'\n'}
//                Shake or press menu button for dev menu
//             </Text>
//        </View>
//        );
//    }
//}
//class MyScene extends Component{
//    static defaultProps = {
//        title: 'PageTwo'
//    };
//    render(){
//        return(
//        <View style={styles.container}>
//        <TouchableHighlight onPress={this.props.onBack}>
//        <Text>BACK</Text>
//        </TouchableHighlight>
//            <Text style={styles.welcome}>
//                Welcome to RN PageTwo!
//            </Text>
//            <Text style={styles.instructions}>
//                To get started, edit index.android.js
//            </Text>
//            <Text style={styles.instructions}>
//                Double tap R on your keyboard to reload,{'\n'}
//                Shake or press menu button for dev menu
//             </Text>
//        </View>
//        );
//    }
//}
const styles = StyleSheet.create({
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

AppRegistry.registerComponent('addScene', () => addScene);

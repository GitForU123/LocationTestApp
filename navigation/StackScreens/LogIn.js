import React, {useState} from 'react';
import {
  View,
  Text,
  StyleSheet,
  TextInput,
  TouchableHighlight,
  ToastAndroid,
} from 'react-native';
import Feather from 'react-native-vector-icons/Feather';

const LogIn = ({navigation}) => {
  const [emailAddress, setEmailAddress] = useState('');
  const [password, setPassword] = useState('');
  const [isPasswordShow, setIsPasswordShow] = useState(false);
  function handleSignIn() {
    if (emailAddress && password.length >= 6) {
      navigation.navigate('Home');
    } else {
      //   ToastAndroid.show('please put valid credentials', ToastAndroid.SHORT);
      //   throw new Error('not valid credentials');
    }
  }
  return (
    <View style={styles.container}>
      <View style={styles.inputWrapper}>
        <Feather name="mail" style={styles.iconStyle} />
        <TextInput
          style={styles.input}
          onChangeText={text => setEmailAddress(text)}
          value={emailAddress}
          textContentType="emailAddress"
          keyboardType="email-address"
          placeholder="Email Address"></TextInput>
      </View>
      <View style={styles.inputWrapper}>
        <Feather name="lock" style={styles.iconStyle} />
        <TextInput
          style={styles.input}
          onChangeText={text => setPassword(text)}
          value={password}
          textContentType="password"
          placeholder="Min Password length 6"
          secureTextEntry={isPasswordShow ? false : true}></TextInput>
      </View>
      <TouchableHighlight
        testID="loginButton"
        style={styles.button}
        onPress={() => handleSignIn()}>
        <Text style={styles.buttonText}>LOGIN</Text>
      </TouchableHighlight>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1, // match parent
    justifyContent: 'flex-start',
  },
  inputWrapper: {
    flexDirection: 'row',
    backgroundColor: 'white',
    height: 50,
    borderRadius: 5,
    borderWidth: 1,
    borderColor: 'skyblue',
    margin: 8,
  },
  input: {
    flex: 1,
    fontSize: 18,
    padding: 5,
  },
  iconStyle: {
    fontSize: 25,
    marginHorizontal: 10,
    alignSelf: 'center',
  },
  button: {
    margin: 10,
    backgroundColor: 'steelblue',
    height: 40,
    justifyContent: 'center',
    alignItems: 'center',
  },
  buttonText: {
    fontSize: 20,
    fontWeight: '800',
    color: 'white',
  },
  buttonCancel: {
    backgroundColor: 'grey',
  },
});

export default LogIn;

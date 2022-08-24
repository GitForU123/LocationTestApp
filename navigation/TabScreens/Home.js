import React from 'react';
import {View, Text, Button} from 'react-native';

const Home = ({navigation}) => {
  return (
    <View>
      <Text>Home page</Text>
      <Button
        testID="logInButton"
        title="go to maps"
        onPress={() => navigation.navigate('Map')}
      />
    </View>
  );
};

export default Home;

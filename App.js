
import React from 'react';
import { View, Text, StyleSheet } from 'react-native';
import { createStackNavigator } from '@react-navigation/stack';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import { NavigationContainer } from '@react-navigation/native';
import Home from './navigation/TabScreens/Home';
import List from './navigation/TabScreens/List';
import Map from './navigation/StackScreens/Map';
import Register from './navigation/StackScreens/Register';
import LogIn from './navigation/StackScreens/LogIn';
import MaterialIcons from 'react-native-vector-icons/MaterialIcons';
const Stack = createStackNavigator();
const Tab = createBottomTabNavigator();

const MapScreens = () =>{
  return(
    <Stack.Navigator initialRouteName = "Home" >
      <Stack.Screen name = "Home" component = {Home} />
      <Stack.Screen name = "Map" component = {Map} />
      <Stack.Screen name = "Register" component = {Register} />
      <Stack.Screen name = "LogIn" component = {LogIn} />
    </Stack.Navigator>
  )
} 

const App = () =>{
  return(
<NavigationContainer>
  <Tab.Navigator screenOptions = {tabBarOptions}>
    <Tab.Screen name = "MapStack" component = {MapScreens}  options={{
          headerShown : false,
            tabBarIcon: ({color, size}) => (
              <MaterialIcons name="home" color={color} size={size} />
            ),
          }}/>
    <Tab.Screen name = "List" component = {List}  options={{
            tabBarIcon: ({color, size}) => (
              <MaterialIcons name='list' color={color} size={size} />
            ),
          }}/>
  <Tab.Screen name = "Profile" component = {Profile}  options={{
            tabBarIcon: ({color, size}) => (
              <MaterialIcons name='profile' color={color} size={size} />
            ),
          }}/>
  </Tab.Navigator>
</NavigationContainer>
  )
}


const tabBarOptions = {
  showLabel: false,
  
  activeTintColor: '#9381ff',
  style: {
    height: '10%',
  },
};

export default App;

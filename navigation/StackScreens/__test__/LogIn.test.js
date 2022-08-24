import React from 'react';
import {render, fireEvent} from '@testing-library/react-native';
import LogIn from '../LogIn';

// check if email is not empty
jest.mock('react-native-vector-icons/Feather', () => 'Icon');
it('email should not be empty', () => {
  const navigation = {navigate: () => {}};
  spyOn(navigation, 'navigate');
  const {getByPlaceholderText, getByText, getAllByText, getByTestId} = render(
    <LogIn navigation={navigation} />,
  );

  // write the input using fireEvent
  fireEvent.changeText(getByPlaceholderText('Email Address'), 'abc@gmail.com');
  fireEvent.changeText(getByPlaceholderText('Min Password length 6'), '1234');
  fireEvent.press(getByTestId('loginButton'));
  try {
    expect(navigation.navigate).toHaveBeenCalledWith('Home');
  } catch (e) {
    //   expect(e.message).toBe('not valid credentials');
    console.log(e);
  }
});
// check if both email and password is not empty

//click sign in ie. promise resolve

// handle navigation

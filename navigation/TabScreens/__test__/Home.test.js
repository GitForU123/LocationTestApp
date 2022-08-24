import React from 'react';
import Home from '../../TabScreens/Home';
const {render, fireEvent} = require('@testing-library/react-native');

describe('home page', () => {
  it('should go to map page', () => {
    const navigation = {navigate: () => {}};
    spyOn(navigation, 'navigate');
    const page = render(<Home navigation={navigation} />);
    const logInButton = page.getByTestId('logInButton');
    fireEvent.press(logInButton);
    expect(navigation.navigate).toHaveBeenCalledWith('Map');
  });
});

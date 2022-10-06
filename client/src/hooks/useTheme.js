import { useState } from 'react';
import { getFromLocalStorage, setToLocalStorage } from '../lib/localStorage';
export const useTheme = () => {
  const isBrowserDarkMode =
    window.matchMedia &&
    window.matchMedia('(prefers-color-scheme: dark)').matches;
  let initTheme = isBrowserDarkMode ? 'night' : 'light';

  const localSettingTheme = getFromLocalStorage('theme');

  if (localSettingTheme) {
    initTheme = localSettingTheme;
  }

  const [themeMode, setThemeMode] = useState(initTheme);

  const setMode = (mode) => {
    setToLocalStorage('theme', mode);
    setThemeMode(mode);
  };

  return [themeMode, setMode];
};

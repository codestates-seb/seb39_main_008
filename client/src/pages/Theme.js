import { useEffect } from 'react';
const theme = ({ setHeaderData }) => {
  useEffect(() => {
    setHeaderData({
      title: '나만의 DUSKHOUR',
      description: '색다른 일기장을 만들어 보세요',
    });
  }, []);

  return (
    <div>
      <input id="text" type="color" />
      <input id="sidebar" type="color" />
      <input id="" type="color" />
    </div>
  );
};

export default theme;

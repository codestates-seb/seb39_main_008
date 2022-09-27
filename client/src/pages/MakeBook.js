import { useEffect } from 'react';
import MakeorEditBookForm from '../components/MakeorEditBookForm';
const MakeBook = ({ setHeaderData }) => {
  useEffect(async () => {
    setHeaderData({
      title: '새 일기장 만들기',
      description: '색다른 기록들을 남길 새 일기장을 만들어보세요',
    });
  }, []);
  return <MakeorEditBookForm />;
};

export default MakeBook;

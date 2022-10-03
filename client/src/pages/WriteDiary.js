import { useEffect } from 'react';
import WriteOrEditDiaryForm from '../components/WriteOrEditDiaryForm';

const WriteDiary = ({ setHeaderData }) => {
  useEffect(async () => {
    setHeaderData({
      title: '새 기록 남기기',
      description: '어떤 기록을 남기고 싶으세요?',
    });
  }, []);

  return <WriteOrEditDiaryForm />;
};

export default WriteDiary;

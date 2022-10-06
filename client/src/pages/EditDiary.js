import { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import WriteOrEditDiaryForm from '../components/WriteOrEditDiaryForm';
import { getDiary } from '../lib/axios';

const EditDiary = ({ setHeaderData }) => {
  const { diaryId } = useParams();

  const [diary, setDiary] = useState(null);

  useEffect(async () => {
    setHeaderData({
      title: '기록 수정하기',
      description: '어떤 기록을 바꾸고 싶으신가요?',
    });
    const res = await getDiary(diaryId);
    setDiary(res.data);
  }, [diaryId]);

  return <>{diary && <WriteOrEditDiaryForm isEdit={true} data={diary} />}</>;
};

export default EditDiary;

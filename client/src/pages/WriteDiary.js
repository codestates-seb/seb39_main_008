import { useEffect } from 'react';
import WriteOrEditDiaryForm from '../components/WriteOrEditDiaryForm';
// import { getBookList } from '../lib/axios';

const WriteDiary = ({ setHeaderData }) => {
  // const [booklist, setBooklist] = useState([]);

  useEffect(async () => {
    setHeaderData({
      title: '새 기록 남기기',
      description: '어떤 기록을 남기고 싶으세요?',
    });
  }, []);
  // useEffect(async () => {
  //   const data = await getBookList(19);
  //   setBooklist(data.data);
  //   console.log(booklist);
  // }, []);

  return <WriteOrEditDiaryForm />;
};

export default WriteDiary;

// '<ul><li class="ql-indent-1">asetasetassdagsasetset…yle="color: rgb(230, 0, 0);">egd</span></li></ul>'

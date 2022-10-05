import { useEffect } from 'react';
import WriteOrEditDiaryForm from '../components/WriteOrEditDiaryForm';
import styled from 'styled-components';
const Container = styled.div``;
const WriteDiary = ({ setHeaderData }) => {
  useEffect(async () => {
    setHeaderData({
      title: '새 기록 남기기',
      description: '어떤 기록을 남기고 싶으세요?',
    });
  }, []);

  return (
    <Container>
      <WriteOrEditDiaryForm />
    </Container>
  );
};

export default WriteDiary;

import Avatar from '../common/Avatar';
import Date from '../common/Date';
import BorderButton from '../common/BorderButton';
import styled from 'styled-components';
import { borderRadius, colors, space } from '../../assets/styles/theme';

const Comment = ({ data }) => {
  const Container = styled.div`
    padding: ${space.spaceS};
    display: flex;
    border-bottom: 1px solid ${colors.grey};

    & > img {
      margin-right: ${space.spaceS};
    }
  `;

  const Top = styled.div`
    margin-bottom: ${space.spaceS};
    display: flex;
    align-items: center;

    & > span {
      margin-right: ${space.spaceS};
      color: ${colors.text3};
    }

    & > span + p {
      display: inline-block;
    }

    & > button:nth-child(3) {
      margin-left: auto;
      margin-right: ${space.spaceS};
    }
  `;

  return (
    <Container>
      <Avatar
        alt={data.nickname}
        imageURL={data.profile}
        width={'50px'}
        height={'50px'}
        borderRadius={borderRadius.borderRadiusL}
      />
      <div>
        <Top>
          <span>{data.nickname}</span>
          <Date date={data.createdAt} />
          <BorderButton text="수정" />
          <BorderButton text="삭제" />
        </Top>
        <p>{data.content}</p>
      </div>
    </Container>
  );
};
export default Comment;

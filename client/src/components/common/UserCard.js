import styled from 'styled-components';
import { theme } from '../../assets/styles/theme';
import { FiUserCheck, FiUserX, FiBook } from 'react-icons/fi';
import { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import Avatar from './Avatar';

const UserCardContainer = styled.div`
  width: 150px;
  height: auto;
  padding: ${theme.space.spaceS};
  border-radius: ${theme.borderRadius.borderRadiusS};
  box-shadow: ${theme.boxShadow.shadowS};
`;

const UserCardContent = styled.div`
  font-size: ${theme.fontSize.fontSizeS};

  > p {
    color: ${theme.colors.text4};
    padding: ${theme.space.spaceS} 0;
  }

  > p:hover {
    color: ${theme.colors.text2};
  }

  > div {
    display: flex;
    justify-content: flex-end;
  }

  > div > p {
    margin-left: ${theme.space.spaceS};
  }

  > div > div > svg {
    cursor: pointer;
  }
`;

const UserCard = ({
  nickname,
  userImage,
  total_content,
  isFollow,
  follower,
  memberId,
}) => {
  const [isCurrnetFollow, setIsFollow] = useState(null);
  const [currnetFollower, setCurrnetFollower] = useState(null);

  useEffect(() => {
    setIsFollow(isFollow);
    setCurrnetFollower(follower);
  }, [isFollow]);

  const handleFollow = async () => {
    try {
      // 팔로우 요청 함수 자리
      if (isCurrnetFollow) {
        setCurrnetFollower((prev) => --prev);
      } else {
        setCurrnetFollower((prev) => ++prev);
      }
      setIsFollow(!isCurrnetFollow);
    } catch (e) {
      console.log(e);
    }
  };

  return (
    <UserCardContainer>
      <Avatar
        alt={nickname}
        imageURL={userImage}
        width={'100%'}
        height={'131px'}
        borderRadius={theme.borderRadius.borderRadiusS}
      />
      <UserCardContent>
        <p>
          <Link to={`/user/${memberId}`}>{nickname}</Link>
        </p>
        <div>
          <div>
            {isCurrnetFollow ? (
              <>
                {currnetFollower} <FiUserCheck onClick={handleFollow} />
              </>
            ) : (
              <>
                {currnetFollower} <FiUserX onClick={handleFollow} />
              </>
            )}
          </div>
          <p>
            {total_content}{' '}
            <Link to={`/books/${memberId}`}>
              <FiBook />
            </Link>
          </p>
        </div>
      </UserCardContent>
    </UserCardContainer>
  );
};

export default UserCard;

import styled from 'styled-components';
import { FiUserCheck, FiUserX, FiBook } from 'react-icons/fi';
import { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import Avatar from './Avatar';

const UserCardContainer = styled.div`
  background-color: ${({ theme }) => theme.colors.custom5};
  width: 150px;
  height: auto;
  flex: 0 0 auto;
  padding: var(--spaceS);
  border-radius: var(--borderRadiusS);
  box-shadow: var(--shadowS);
`;

const UserCardContent = styled.div`
  font-size: var(--fontSizeS);
  color: ${({ theme }) => theme.colors.text1};
  > p {
    color: ${({ theme }) => theme.colors.text4};
    padding: var(--spaceS) 0;
  }

  > p:hover {
    color: ${({ theme }) => theme.colors.text1};
  }

  > div {
    display: flex;
    justify-content: flex-end;
  }

  > div > p {
    margin-left: var(--spaceS);
  }

  > div > div > svg {
    cursor: pointer;
  }
`;

const UserCard = ({
  nickname,
  userImage,
  total_content,
  total_follower,
  isFollow,
  memberId,
}) => {
  const [isCurrnetFollow, setIsFollow] = useState(null);
  const [currnetFollower, setCurrnetFollower] = useState(null);

  useEffect(() => {
    setIsFollow(isFollow);
    setCurrnetFollower(total_follower);
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
        borderRadius={`var(--borderRadiusS)`}
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
            {total_content}
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

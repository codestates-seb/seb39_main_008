import styled from 'styled-components';
import { FiUserCheck, FiUserX } from 'react-icons/fi';
import { Link } from 'react-router-dom';
import { useState, useEffect } from 'react';
import Avatar from './Avatar';

const ProfileContainer = styled.div`
  display: flex;
`;

const ProfileContentsBox = styled.div`
  margin-left: ${({ theme }) => theme.space.spaceS};
  display: flex;
  flex-direction: column;
  justify-content: flex-end;

  > p > svg {
    cursor: pointer;
  }

  > p:first-child {
    margin-bottom: ${({ theme }) => theme.space.spaceS};
  }

  > p:last-child {
    color: ${({ theme }) => theme.colors.text4};
    margin-bottom: calc(${({ theme }) => theme.space.spaceS} / 3);
  }

  > p:last-child:hover {
    color: ${({ theme }) => theme.colors.text2};
  }
`;

const Profile = ({ userImage, isFollow, nickname, memberId }) => {
  const [isCurrnetFollow, setIsFollow] = useState(null);

  useEffect(() => {
    setIsFollow(isFollow);
  }, [isFollow]);

  const handleFollow = async () => {
    try {
      // 팔로우 요청 함수 자리
      console.log(isCurrnetFollow);
      setIsFollow(!isCurrnetFollow);
    } catch (e) {
      console.log(e);
    }
  };

  return (
    <ProfileContainer>
      <Avatar
        alt={nickname}
        imageURL={userImage}
        width={'50px'}
        height={'50px'}
        borderRadius={`${({ theme }) => theme.borderRadius.borderRadiusL}`}
      />
      <ProfileContentsBox>
        <p>
          {isCurrnetFollow ? (
            <FiUserCheck onClick={handleFollow} />
          ) : (
            <FiUserX onClick={handleFollow} />
          )}
        </p>
        <p>
          <Link to={`/user/${memberId}`}>{nickname}</Link>
        </p>
      </ProfileContentsBox>
    </ProfileContainer>
  );
};

export default Profile;

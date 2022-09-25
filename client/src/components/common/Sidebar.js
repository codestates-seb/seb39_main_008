import { useState } from 'react';
import styled from 'styled-components';
import Logo from './Logo';
import Avatar from './Avatar';
import { theme, colors } from '../../assets/styles/theme';
import { useNavigate } from 'react-router-dom';
import {
  FiPenTool,
  FiHome,
  FiBookOpen,
  FiUsers,
  FiLogOut,
} from 'react-icons/fi';
import { RiUserSettingsLine } from 'react-icons/ri';
import { IoColorPaletteOutline } from 'react-icons/io5';
import ComfirmModal from './ComfirmModal';

const Wraper = styled.div`
  * {
    font-size: ${theme.fontSize.fontSizeM};
  }
  z-index: 1000;
  display: flex;
  padding: ${theme.space.spaceM};
  max-width: 223px;
  height: 100vh;
  border-right: 2px solid ${colors.grey};
  position: sticky;
  top: 0;
  flex-direction: column;
  .auto {
    margin-top: auto;
  }
  .auto + div {
    margin-top: ${theme.space.spaceS};
  }
`;
const UserBox = styled.div`
  margin-top: 20px;
  & > button {
    width: 100%;
    position: relative;
    margin-top: ${theme.space.spaceM};

    & > div {
      color: ${colors.text2};
      text-align: left;
    }
    & > p:nth-child(2) {
      color: ${colors.text4};
    }
    & > svg {
      color: ${colors.text4};
      position: absolute;
      bottom: 0;
      right: 0;
    }
  }
  & > button:hover {
    & > svg {
      color: ${colors.text3};
    }
  }
`;
const List = styled.div`
  margin-top: ${theme.space.spaceL};
  & > button {
    width: 100%;
    display: flex;
    justify-content: space-between;
    color: ${colors.text2};
    & > svg {
      color: ${colors.text4};
    }
  }

  & > button:hover {
    & > svg {
      color: ${colors.text3};
    }
  }
`;
const Sidebar = ({ hasSidebar }) => {
  const [confirm, setConfirm] = useState(false);
  const onLogout = () => {
    setConfirm(!confirm);
  };
  const logOut = async () => {
    //todo 로그아웃 구현
    navigate('/');
  };

  const confirmLogout = (res) => {
    if (res) {
      onLogout();
      logOut();
    } else {
      onLogout();
    }
  };
  const listdata = {
    newDiary: {
      text: '새 기록 남기기',
      icon: <FiPenTool size={20} />,
      onClick: () => {
        navigate('/writediary');
      },
    },
    main: {
      text: '홈',
      icon: <FiHome size={20} />,
      onClick: () => {
        navigate('/');
      },
    },
    books: {
      text: '내 일기장',
      icon: <FiBookOpen size={20} />,
      onClick: () => {
        navigate('/books/1');
      },
    },
    people: {
      text: '사람들',
      icon: <FiUsers size={20} />,
      onClick: () => {
        navigate('/people');
      },
    },
    logout: {
      text: '로그아웃',
      icon: <FiLogOut size={20} />,
      className: 'auto',
      onClick: () => {
        setConfirm(!confirm);
      },
    },
    setTheme: {
      text: '테마설정',
      icon: <IoColorPaletteOutline size={20} />,
      onClick: () => {
        console.log('set theme');
      },
    },
  };
  const list = Object.values(listdata);
  const userData = {
    name: '김코딩',
    profileURL:
      'https://images.unsplash.com/photo-1438761681033-6461ffad8d80?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80',
    nickname: 'codingjoa',
    icon: <RiUserSettingsLine size={20} />,
    onClick: () => {
      navigate('/user/1');
    },
  };
  const navigate = useNavigate();
  return (
    <>
      <Wraper hasSidebar={hasSidebar}>
        {confirm && (
          <ComfirmModal
            message={'로그아웃 하시겠습니까?'}
            onComfirm={confirmLogout}
            target={`${userData.nickname} 님`}
          />
        )}
        <Logo />
        <UserBox>
          <Avatar
            isShadow={true}
            height="197px"
            imageURL={userData.profileURL}
            borderRadius={theme.borderRadius.borderRadiusL}
          />
          <button onClick={userData.onClick}>
            <div>
              <p>{userData.name}</p>
              <p>{userData.nickname}</p>
            </div>
            {userData.icon}
          </button>
        </UserBox>
        {list.map((el, idx) => {
          return (
            <List key={idx} className={el.className || 'item'}>
              <button onClick={el.onClick}>
                <p>{el.text}</p>
                {el.icon}
              </button>
            </List>
          );
        })}
      </Wraper>
    </>
  );
};

export default Sidebar;

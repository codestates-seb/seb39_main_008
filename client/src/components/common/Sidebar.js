import { useState } from 'react';
import styled from 'styled-components';
import Logo from './Logo';
import Avatar from './Avatar';
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
import ConfirmModal from './ConfirmModal';

const Wraper = styled.div`
  * {
    font-size: ${({ theme }) => theme.fontSize.fontSizeM};
  }

  z-index: 1000;
  display: flex;
  padding: ${({ theme }) => theme.space.spaceM};
  width: 223px;
  min-width: 223px;
  height: 100vh;
  border-right: 2px solid ${({ theme }) => theme.colors.grey};
  position: sticky;
  top: 0;
  flex-direction: column;
  background-color: ${({ theme }) => theme.colors.white};

  .auto {
    margin-top: auto;
  }

  .auto + div {
    margin-top: ${({ theme }) => theme.space.spaceS};
  }

  @media ${({ theme }) => theme.screen.mobileAndTablet} {
    position: absolute;
    left: -${({ theme }) => theme.space.spaceS};
    height: calc(100vh - 50px);
    top: 49px;

    & > div:first-child {
      display: none;
    }
  }
`;

const UserBox = styled.div`
  margin-top: 20px;

  & > button {
    width: 100%;
    position: relative;
    margin-top: ${({ theme }) => theme.space.spaceM};

    & > div {
      color: ${({ theme }) => theme.colors.text2};
      text-align: left;
    }

    & > div:hover {
      color: ${({ theme }) => theme.colors.text4};
    }

    & > p:nth-child(2) {
      color: ${({ theme }) => theme.colors.text4};
    }

    & > svg {
      color: ${({ theme }) => theme.colors.text4};
      position: absolute;
      bottom: 0;
      right: 0;
    }
  }

  & > button:hover {
    & > svg {
      color: ${({ theme }) => theme.colors.text3};
    }
  }

  @media ${({ theme }) => theme.screen.mobileAndTablet} {
    margin-top: 0px;
  }
`;
const List = styled.div`
  margin-top: ${({ theme }) => theme.space.spaceL};

  & > button {
    width: 100%;
    display: flex;
    justify-content: space-between;
    color: ${({ theme }) => theme.colors.text2};

    & > svg {
      color: ${({ theme }) => theme.colors.text4};
    }
  }

  & > button:hover {
    color: ${({ theme }) => theme.colors.text4};

    & > svg {
      color: ${({ theme }) => theme.colors.text3};
    }
  }
`;

const Sidebar = ({ hasSidebar }) => {
  const [confirm, setConfirm] = useState(false);

  const navigate = useNavigate();

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

  const LISTDATA = {
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
        navigate('/theme');
      },
    },
  };

  const list = Object.values(LISTDATA);

  const USERDATA = {
    name: '김코딩',
    profileURL:
      'https://images.unsplash.com/photo-1438761681033-6461ffad8d80?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80',
    nickname: 'codingjoa',
    icon: <RiUserSettingsLine size={20} />,
    onClick: () => {
      navigate('/user/1');
    },
  };

  return (
    <>
      <Wraper hasSidebar={hasSidebar}>
        <Logo />
        {confirm && (
          <ConfirmModal
            message={'로그아웃 하시겠습니까?'}
            onComfirm={confirmLogout}
            target={`${USERDATA.nickname} 님`}
          />
        )}

        <UserBox>
          <Avatar
            isShadow={true}
            height="197px"
            imageURL={USERDATA.profileURL}
            borderRadius={({ theme }) => theme.borderRadius.borderRadiusL}
          />
          <button onClick={USERDATA.onClick}>
            <div>
              <p>{USERDATA.name}</p>
              <p>{USERDATA.nickname}</p>
            </div>
            {USERDATA.icon}
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

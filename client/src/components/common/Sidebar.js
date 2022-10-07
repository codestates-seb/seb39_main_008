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
    font-size: var(--fontSizeM);
  }

  background-color: ${({ theme }) => theme.colors.sidebar};
  z-index: 1000;
  display: flex;
  padding: var(--spaceM);
  width: 223px;
  min-width: 223px;
  height: 100vh;
  border-right: 2px solid ${({ theme }) => theme.colors.border};
  position: sticky;
  top: 0;
  flex-direction: column;

  .auto {
    margin-top: auto;
  }

  .auto + div {
    margin-top: var(--spaceS);
  }

  @media screen and (max-width: 991.98px) {
    position: absolute;
    left: -var(--spaceS);
    height: calc(100vh - 50px);
    top: 49px;

    & > div:first-child {
      display: none;
    }
  }
  @media screen and (max-width: 576px) {
    * {
      font-size: var(--fontSizeS);
      > svg {
        width: var(--fontSizeM);
      }
    }
    width: 140px;
    min-width: 140px;
  }
`;

const UserBox = styled.div`
  margin-top: 20px;

  & > button {
    width: 100%;
    position: relative;
    margin-top: var(--spaceM);

    & > div {
      color: ${({ theme }) => theme.colors.text1};
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
      color: ${({ theme }) => theme.colors.text1};
    }
  }

  @media screen and (max-width: 991.98px) {
    margin-top: 0px;
  }

  @media screen and (max-width: 576px) {
    > img:nth-child(1) {
      width: 100px;
      height: 100px;
    }

    & > button {
      margin-top: var(--spaceS);
    }
  }
`;
const List = styled.div`
  margin-top: var(--spaceL);

  & > button {
    width: 100%;
    display: flex;
    justify-content: space-between;
    color: ${({ theme }) => theme.colors.text1};

    & > svg {
      color: ${({ theme }) => theme.colors.text4};
    }
  }

  & > button:hover {
    color: ${({ theme }) => theme.colors.text4};

    & > svg {
      color: ${({ theme }) => theme.colors.text1};
    }
  }
  @media screen and (max-width: 576px) {
    margin-top: var(--spaceM);
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
    setTheme: {
      text: '테마설정',
      className: 'auto',
      icon: <IoColorPaletteOutline size={20} />,
      onClick: () => {
        navigate('/theme');
      },
    },
    logout: {
      text: '로그아웃',
      icon: <FiLogOut size={20} />,

      onClick: () => {
        setConfirm(!confirm);
      },
    },
  };

  const list = Object.values(LISTDATA);

  const USERDATA = {
    id: 1,
    email: 'dsinnie0@geocities.jp',
    password: 'sYVpE41iTW',
    name: 'Dud Sinnie',
    nickname: 'Sinnie',
    infomation: 'Guaifenesin',
    total_follower: 20,
    total_following: 35,
    image:
      'https://cdn.pixabay.com/photo/2018/02/20/20/52/people-3168830_960_720.jpg',
    isFollow: true,
    created_at: '2022-09-15 09:26:06',
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
            imageURL={USERDATA.image}
            borderRadius={`var(--borderRadiusL)`}
          />
          <button
            onClick={() => {
              navigate(`/user/${USERDATA.id}`);
            }}
          >
            <div>
              <p>{USERDATA.name}</p>
              <p>{USERDATA.nickname}</p>
            </div>
            <RiUserSettingsLine size={20} />
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

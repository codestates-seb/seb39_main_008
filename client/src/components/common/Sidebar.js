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

const Wraper = styled.div`
  * {
    font-size: ${theme.fontSize.fontSizeM};
    box-sizing: border-box;
  }
  display: ${(props) => (props.hasSidebar ? 'block' : 'none')};
  padding: ${theme.space.spaceM};
  max-width: 223px;
  height: 100vh;
  border-right: 2px solid ${colors.grey};
  position: sticky;
  top: 0;
  display: flex;
  flex-direction: column;
`;
const UserBox = styled.div`
  & > button {
    padding: 0;
    width: 100%;
    position: relative;
    margin-top: ${theme.space.spaceM};
    & > p {
      color: ${colors.text2};
      text-align: left;
    }
    & > p:nth-child(2) {
      color: ${colors.text4};
    }
    & > svg {
      position: absolute;
      bottom: 0;
      right: 0;
    }
  }
`;

const Sidebar = ({ hasSidebar }) => {
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
      onClick: () => {
        console.log('logout');
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
        <Logo />
        <UserBox>
          <Avatar
            height="197px"
            imageURL={userData.profileURL}
            borderRadius={theme.borderRadius.borderRadiusL}
          />
          <button onClick={userData.onClick}>
            <p>{userData.name}</p>
            <p>{userData.nickname}</p>
            {userData.icon}
          </button>
        </UserBox>
        <SidebarList listdata={listdata}></SidebarList>
      </Wraper>
    </>
  );
};

export default Sidebar;

const SidebarList = ({ listdata }) => {
  const list = Object.values(listdata);
  const List = styled.div`
    width: 100%;
    height: 100%;
    margin-bottom: 0;
    position: relative;
    div:nth-child(5) {
      width: 100%;
      position: absolute;
      bottom: 36px;
    }
    div:nth-child(6) {
      width: 100%;
      bottom: 0;
      position: absolute;
    }
  `;
  const Item = styled.div`
    margin-top: ${theme.space.spaceL};
    & > button {
      padding: 0;
      width: 100%;
      display: flex;
      justify-content: space-between;
      color: ${colors.text2};
    }
  `;
  return (
    <List>
      {list.map((el, idx) => {
        return (
          <Item key={idx}>
            <button onClick={el.onClick}>
              <p>{el.text}</p>
              {el.icon}
            </button>
          </Item>
        );
      })}
    </List>
  );
};

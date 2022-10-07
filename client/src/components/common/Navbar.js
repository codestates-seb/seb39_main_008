import styled from 'styled-components';
import Logo from './Logo';
import Avatar from './Avatar';
import { useRef, useState, useEffect } from 'react';
// import { RiUserSettingsLine } from 'react-icons/ri';
// import { useNavigate } from 'react-router-dom';
import Sidebar from './Sidebar';

const NavContainer = styled.div`
  display: none;
  width: 100%;
  padding: calc(var(--spaceS) / 3) var(--spaceS);
  border-bottom: 1px solid ${({ theme }) => theme.colors.border};
  align-items: center;

  @media screen and (max-width: 991.98px) {
    display: flex;
    justify-content: space-between;
  }
`;

const Toggle = styled.div`
  cursor: pointer;
  position: relative;
  display: flex;
  height: 100%;
  padding: 0 var(--spaceS) 0 0;
  flex-shrink: 0;
  align-items: center;
  justify-content: center;

  & > .hamberger {
    width: 16px;
    height: 2px;
    background-color: ${({ theme }) => theme.colors.text1};
    position: relative;
    box-sizing: border-box;
    cursor: pointer;
  }

  & > .hamberger:after {
    position: absolute;
    content: '';
    left: 0;
    top: 5px;
    transition: top, transform;
    transition-duration: 0.1s;
    transition-timing-function: ease-in-out;
    width: 16px;
    height: 2px;
    background-color: ${({ theme }) => theme.colors.text1};
  }

  & > .hamberger:before {
    position: absolute;
    content: '';
    left: 0;
    top: -5px;
    transition: top, transform;
    transition-duration: 0.1s;
    transition-timing-function: ease-in-out;
    width: 16px;
    height: 2px;
    background-color: ${({ theme }) => theme.colors.text1};
  }

  & > .X-mark {
    background-color: transparent;
    width: 16px;
    height: 2px;
    position: relative;
    color: #0c0d0e;
  }

  & > .X-mark:before {
    top: 0;
    transform: rotate(-45deg);
    position: absolute;
    content: '';
    left: 0;
    transition: top, transform;
    transition-duration: 0.1s;
    transition-timing-function: ease-in-out;
    width: 16px;
    height: 2px;
    background-color: ${({ theme }) => theme.colors.text1};
  }

  & > .X-mark:after {
    transform: rotate(45deg);
    top: 0;
    position: absolute;
    content: '';
    transition: top, transform;
    transition-duration: 0.1s;
    transition-timing-function: ease-in-out;
    width: 16px;
    height: 2px;
    background-color: ${({ theme }) => theme.colors.text1};
  }
`;
const Navbar = () => {
  // const navigate = useNavigate();

  const [sidebar, setSidebar] = useState(false);

  const sidebarRef = useRef();
  const toggleRef = useRef();

  const handleClickOutSide = (e) => {
    if (
      sidebar &&
      !sidebarRef.current.contains(e.target) &&
      !toggleRef.current.contains(e.target)
    ) {
      setSidebar(!sidebar);
    }
  };

  useEffect(() => {
    if (sidebar) window.addEventListener('click', handleClickOutSide);
    return () => {
      window.removeEventListener('click', handleClickOutSide);
    };
  });

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
      {sidebar && (
        <div ref={sidebarRef}>
          <Sidebar />
        </div>
      )}
      <NavContainer>
        <Toggle
          ref={toggleRef}
          onClick={() => {
            setSidebar(!sidebar);
          }}
        >
          <span className={!sidebar ? 'hamberger' : 'X-mark'}></span>
        </Toggle>
        <Logo height={'38px'} />
        <Avatar
          isShadow={true}
          width="40px"
          height="40px"
          imageURL={USERDATA.image}
          borderRadius={`var(--borderRadiusL)`}
        />
      </NavContainer>
    </>
  );
};

export default Navbar;

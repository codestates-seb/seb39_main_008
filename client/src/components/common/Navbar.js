import styled from 'styled-components';
import Logo from './Logo';
import Avatar from './Avatar';
import { useRef, useState, useEffect } from 'react';
import { borderRadius, colors, space } from '../../assets/styles/theme';
import { RiUserSettingsLine } from 'react-icons/ri';
import { useNavigate } from 'react-router-dom';
import Sidebar from './Sidebar';

const NavContainer = styled.div`
  display: none;
  width: 100%;
  padding: calc(${space.spaceS} / 3) ${space.spaceS};
  border-bottom: 1px solid ${colors.grey};
  align-items: center;
  background-color: ${colors.white};
  @media ${screen.mobileAndTablet} {
    display: flex;
    justify-content: space-between;
  }
`;

const Toggle = styled.div`
  position: relative;
  display: flex;
  height: 100%;
  padding: 0 ${space.spaceS} 0 0;
  flex-shrink: 0;
  align-items: center;
  justify-content: center;

  & > .hamberger {
    width: 16px;
    height: 2px;
    background-color: ${colors.black};
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
    background-color: ${colors.black};
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
    background-color: ${colors.black};
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
    background-color: ${colors.black};
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
    background-color: ${colors.black};
  }
`;
const Navbar = () => {
  const navigate = useNavigate();

  const [sidebar, setSidebar] = useState(false);

  const sidebarRef = useRef();
  const toggleRef = useRef();

  const handleClickOutSide = (e) => {
    e.preventDefault();
    if (
      sidebar &&
      !sidebarRef.current.contains(e.target) &&
      !toggleRef.current.contains(e.target)
    ) {
      setSidebar(false);
    }
  };

  useEffect(() => {
    if (sidebar) document.addEventListener('mousedown', handleClickOutSide);
    return () => {
      document.removeEventListener('mousedown', handleClickOutSide);
    };
  });

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
          imageURL={USERDATA.profileURL}
          borderRadius={borderRadius.borderRadiusL}
        />
      </NavContainer>
    </>
  );
};

export default Navbar;

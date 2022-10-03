import Sidebar from '../components/common/Sidebar';
import Header from '../components/common/Header';
import styled from 'styled-components';
import React, { useState } from 'react';
import { screen, space } from '../assets/styles/theme';
import Navbar from '../components/common/Navbar';

const Container = styled.div`
  margin: 0 auto;
  max-width: 1200px;
  display: flex;

  @media ${screen.mobileAndTablet} {
    flex-direction: column;
  }
`;
const Content = styled.section`
  overflow-x: hidden;
  margin-left: ${(props) => (props.hasCommon ? '20px' : '0px')};
  width: 100%;
  max-width: ${(props) => (props.hasCommon ? '977px' : '1200px')};
  padding: ${space.spaceL};

  @media ${screen.mobileAndTablet} {
    margin-left: 0px;
  }

  @media ${screen.tablet} {
    padding: ${space.spaceM};
  }

  @media ${screen.mobile} {
    padding: calc(${space.spaceS} + 6px);
  }
`;
const Aside = styled.aside`
  @media ${screen.mobileAndTablet} {
    display: none;
  }
`;
const NavContainer = styled.div`
  display: none;
  @media ${screen.mobileAndTablet} {
    display: flex;
    position: sticky;
    top: 0;
  }
`;
function Layout({ hasCommon = true, children }) {
  const [headerData, setHeaderData] = useState({});
  return (
    <Container>
      {hasCommon && (
        <>
          <Aside>
            <Sidebar />
          </Aside>
          <NavContainer>
            <Navbar />
          </NavContainer>
        </>
      )}
      <Content hasCommon={hasCommon}>
        {hasCommon && <Header header={headerData} />}
        {React.cloneElement(children, { setHeaderData })}
      </Content>
    </Container>
  );
}

export default Layout;

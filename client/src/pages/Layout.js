import Sidebar from '../components/common/Sidebar';
import Header from '../components/common/Header';
import styled from 'styled-components';
import React, { useState } from 'react';
import Navbar from '../components/common/Navbar';

const Container = styled.div`
  margin: 0 auto;
  max-width: 1200px;
  display: flex;

  @media ${({ theme }) => theme.screen.mobileAndTablet} {
    flex-direction: column;
  }
`;

const Content = styled.section`
  overflow-x: hidden;
  margin-left: ${(props) => (props.hasCommon ? '20px' : '0px')};
  width: 100%;
  max-width: ${(props) => (props.hasCommon ? '977px' : '1200px')};
  padding: ${({ theme }) => theme.space.spaceL};

  @media ${({ theme }) => theme.screen.mobileAndTablet} {
    margin-left: 0px;
  }

  @media ${({ theme }) => theme.screen.tablet} {
    padding: ${({ theme }) => theme.space.spaceM};
  }

  @media ${({ theme }) => theme.screen.mobile} {
    padding: calc(${({ theme }) => theme.space.spaceS} + 6px);
  }
`;

const Aside = styled.aside`
  @media ${({ theme }) => theme.screen.mobileAndTablet} {
    display: none;
  }
`;

const NavContainer = styled.div`
  display: none;

  @media ${({ theme }) => theme.screen.mobileAndTablet} {
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

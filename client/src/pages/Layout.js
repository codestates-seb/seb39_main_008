import Sidebar from '../components/common/Sidebar';
import Header from '../components/common/Header';
import styled from 'styled-components';
import React, { useState } from 'react';
import Navbar from '../components/common/Navbar';

const Container = styled.div`
  margin: 0 auto;
  max-width: 1200px;
  display: flex;

  @media screen and (max-width: 991.98px) {
    flex-direction: column;
  }
`;

const Content = styled.section`
  overflow-x: hidden;
  margin-left: ${(props) => (props.hasCommon ? `var(--spaceM)` : '0px')};
  width: 100%;
  max-width: ${(props) => (props.hasCommon ? '977px' : '1200px')};
  padding: var(--spaceL);

  @media screen and (max-width: 991.98px) {
    margin-left: 0px;
  }

  @media screen and (min-width: 576px) and (max-width: 991.98px) {
    padding: var(--spaceM);
  }

  @media screen and (max-width: 576px) {
    padding: calc(var(--spaceS) + 6px);
  }
`;

const Aside = styled.aside`
  @media screen and (max-width: 991.98px) {
    display: none;
  }
`;

const NavContainer = styled.div`
  display: none;

  @media screen and (max-width: 991.98px) {
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

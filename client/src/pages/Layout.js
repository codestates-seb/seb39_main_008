import Sidebar from '../components/common/Sidebar';
import Header from '../components/common/Header';
import styled from 'styled-components';
import React, { useState } from 'react';

const Container = styled.div`
  margin: 0 auto;
  max-width: 1200px;
  display: flex;
`;
const Content = styled.section`
  margin-left: ${(props) => (props.hasCommon ? '20px' : '0px')};
  width: 100%;
  max-width: ${(props) => (props.hasCommon ? '977px' : '1200px')};
`;
function Layout({ hasCommon = true, children }) {
  const [headerData, setHeaderData] = useState({});
  return (
    <Container>
      {hasCommon && <Sidebar />}
      <Content hasCommon={hasCommon}>
        {hasCommon && <Header header={headerData} />}
        {React.cloneElement(children, { setHeaderData })}
      </Content>
    </Container>
  );
}

export default Layout;

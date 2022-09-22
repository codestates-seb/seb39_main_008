import Sidebar from '../components/common/Sidebar';
import Header from '../components/common/Header';
import styled from 'styled-components';

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
function Layout({ hasCommon = true, ...props }) {
  return (
    <Container>
      {hasCommon && <Sidebar />}
      <Content hasCommon={hasCommon}>
        {hasCommon && <Header />}
        {props.children}
      </Content>
    </Container>
  );
}

export default Layout;

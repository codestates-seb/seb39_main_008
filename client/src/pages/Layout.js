import Sidebar from '../components/common/Sidebar';
import Header from '../components/common/Header';
import styled from 'styled-components';

const Container = styled.div`
  margin: 0 auto;
  max-width: 1200px;
  display: flex;
`;
const Content = styled.section`
  background-color: aliceblue;
  margin-left: 20px;
  width: 100%;
  max-width: calc(1200px - 223px);
`;
function Layout({ hasCommon = true, ...props }) {
  return (
    <Container>
      <Sidebar hasSidebar={hasCommon} />
      <Content>
        <Header hasHeader={hasCommon} />
        {props.children}
      </Content>
    </Container>
  );
}

export default Layout;

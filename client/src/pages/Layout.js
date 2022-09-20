import Sidebar from '../components/common/Sidebar';
import Header from '../components/common/Header';
import styled from 'styled-components';

const Body = styled.div`
  width: 100vw;
  height: 100vh;
`;
const Main = styled.section``;
const Layout = ({ hasCommon = true, ...props }) => {
  return (
    <Body>
      <Sidebar hasSidebar={hasCommon} user={props.user} />
      <Main>
        <Header hasHeader={hasCommon} header={props.header} />
        {props.children}
      </Main>
    </Body>
  );
};

export default Layout;

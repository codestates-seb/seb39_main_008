import { theme } from '../../assets/styles/theme';
import Logo from './Logo';
import styled from 'styled-components';

const Wraper = styled.div`
  display: ${(props) => (props.hasSidebar ? 'flex-block' : 'none')};
  flex-direction: column;
  max-width: 223px;
  margin: ${theme.space.spaceM};
  & * {
    margin-top: ${theme.space.spaceM};
  }
`;

const Sidebar = (props) => {
  return (
    <Wraper hasSidebar={props.hasSidebar}>
      <Logo />
    </Wraper>
  );
};

export default Sidebar;

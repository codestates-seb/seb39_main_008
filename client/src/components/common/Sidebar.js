import styled from 'styled-components';
import Logo from './Logo';
import Avatar from './Avatar';
import { theme } from '../../assets/styles/theme';

const Wraper = styled.aside`
  display: ${(props) => (props.hasSidebar ? 'flex' : 'none')};
  flex-direction: column;
  margin: ${theme.space.spaceM};
  max-width: 223px;
  & > * {
    margin-bottom: ${theme.space.spaceM};
  }
`;
const UserBox = styled.div``;

const Sidebar = ({ hasSidebar }) => {
  return (
    <Wraper hasSidebar={hasSidebar}>
      <Logo />
      <UserBox>
        <Avatar
          imageURL={
            'https://www.business2community.com/wp-content/uploads/2017/08/blank-profile-picture-973460_640.png'
          }
          borderRadius={theme.borderRadius.borderRadiusL}
        />
      </UserBox>
    </Wraper>
  );
};

export default Sidebar;

import styled from 'styled-components';
const Container = styled.div`
  display: block;
  padding: ${({ theme }) => theme.space.spaceL};
  max-width: 954px;
`;
const Wraper = styled.div`
  border-bottom: 1px solid ${({ theme }) => theme.colors.grey};
`;
const Title = styled.p`
  font-size: ${({ theme }) => theme.space.spaceL};
  margin-bottom: ${({ theme }) => theme.space.spaceM};
  color: ${({ theme }) => theme.colors.text1};
`;
const Description = styled.p`
  font-size: ${({ theme }) => theme.fontSize.fontSizeM};
  color: ${({ theme }) => theme.colors.text3};
  margin: ${({ theme }) => theme.space.spaceS};
  margin-top: 0;
`;
const Header = (props) => {
  return (
    <Container>
      <Wraper>
        {props.header && (
          <>
            <Title>{props.header.title}</Title>
            <Description>{props.header.description}</Description>
          </>
        )}
      </Wraper>
    </Container>
  );
};

export default Header;

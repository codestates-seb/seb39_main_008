import styled from 'styled-components';

const Container = styled.div`
  display: block;
  padding-bottom: var(--spaceL);
  max-width: 954px;
`;

const Wraper = styled.div`
  border-bottom: 1px solid ${({ theme }) => theme.colors.grey};
`;

const Title = styled.p`
  font-size: var(--fontSizeLL);
  margin-bottom: var(--spaceM);
  color: ${({ theme }) => theme.colors.text1};

  @media screen and (min-width: 576px) and (max-width: 991.98px) {
    font-size: calc(var(--fontSizeM) * 2);
  }

  @media screen and (max-width: 576px) {
    font-size: calc(var(--fontSizeM) * 1.5);
  }
`;

const Description = styled.p`
  font-size: var(--fontSizeM);
  color: ${({ theme }) => theme.colors.text3};
  margin: var(--spaceS);
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

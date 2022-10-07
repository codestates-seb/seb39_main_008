import Logo from '../components/common/Logo';
import styled from 'styled-components';
import img1 from '../assets/img/1-1.png';
import img2 from '../assets/img/1-2.png';
import img3 from '../assets/img/1-3.png';
import img4 from '../assets/img/1-4.png';
import mac from '../assets/img/Macbook.png';
import iPad from '../assets/img/iPad.png';
import iPhone from '../assets/img/iPhone.png';
import user1 from '../assets/img/user1.png';
import user2 from '../assets/img/user2.png';
import user3 from '../assets/img/user3.png';
import user4 from '../assets/img/user4.png';
import TextButton from '../components/common/TextButton';
import { useNavigate } from 'react-router-dom';
import { useEffect } from 'react';
import { setToLocalStorage } from '../lib/localStorage';

const Container = styled.div`
  text-align: center;
`;

const Box1 = styled.div`
  padding-top: var(--spaceL);
`;

const MockupWraper = styled.div`
  position: relative;
  height: 500px;

  @media screen and (max-width: 991.98px) {
    height: 300px;
    & > img:nth-child(1),
    & > img:nth-child(2),
    & > img:nth-child(3),
    & > img:nth-child(4) {
      display: none;
    }
  }

  @media screen and (max-width: 576px) {
    height: 200px;
  }
`;

const AbsoluteImg = styled.img`
  bottom: ${(props) => props.bottom};
  right: ${(props) => props.right};
  position: absolute;
  width: ${(props) => props.width};
`;

const GuideWraper = styled.div`
  margin-top: var(--spaceL);
  display: flex;
  flex-direction: column;
  align-items: center;
  .lastBox {
    margin-bottom: var(--spaceL);
  }
`;

const GuideBox = styled.div`
  align-items: center;
  display: flex;
  width: 90%;
  padding: var(--spaceL);
  border-bottom: 10px dotted ${({ theme }) => theme.colors.text5};

  .last {
    span {
      font-size: calc(var(--fontSizeM) * 1.2);
      display: inline-block;
      margin: var(--spaceM);
    }
  }

  @media screen and (max-width: 576px) {
    flex-direction: column;
  }
`;

const Text = styled.p`
  padding: var(--spaceL);
  font-size: var(--fontSizeL);

  color: ${({ theme }) => theme.colors.text1};

  @media screen and (max-width: 576px) {
    font-size: var(--fontSizeM);
  }
`;

const Img = styled.img`
  @media screen and (max-width: 576px) {
    width: 50%;
  }
`;

const Landing = () => {
  const navigate = useNavigate();

  useEffect(() => {
    setToLocalStorage('/landing', true);
  });

  return (
    <Container>
      <Box1>
        <Logo width="30%" />
        <MockupWraper>
          <AbsoluteImg right={'70%'} bottom={'5%'} width={'35%'} src={img1} />
          <AbsoluteImg right={'60%'} bottom={'20%'} width={'35%'} src={img2} />
          <AbsoluteImg right={'8%'} bottom={'40%'} width={'30%'} src={img3} />
          <AbsoluteImg right={'10%'} bottom={'5%'} width={'18%'} src={img4} />
          <AbsoluteImg right={'28%'} bottom={'0%'} width={'50%'} src={mac} />
          <AbsoluteImg right={'25%'} bottom={'0%'} width={'17%'} src={iPad} />
          <AbsoluteImg right={'20%'} bottom={'0%'} width={'8%'} src={iPhone} />
        </MockupWraper>
        <GuideWraper>
          <GuideBox>
            <Img src={user1}></Img>
            <Text>
              여러 사람들과 함께 읽고 쓰는 공간.
              <br /> 같은 고민, 다른 생각을 공유하며 위로와 공감을 나눠요
            </Text>
          </GuideBox>
          <GuideBox>
            <Text>
              기록은 남기고 싶은데, <br /> 무슨 내용을 적으면 좋을까요? <br />
              DUSKHOUR가 추천하는 주제로 적어 보세요
            </Text>
            <Img src={user2}></Img>
          </GuideBox>
          <GuideBox>
            <Img src={user3}></Img>
            <Text>
              원하는 대로 바꿔보세요 <br /> 나만의 개성이 살아있는 일기장을
              만들어 보세요
            </Text>
          </GuideBox>
          <GuideBox className="lastBox">
            <div className="last">
              <Text>
                지금부터 DUSKHOUR와 <br /> 함께 일상을 기록해보세요
              </Text>
              <TextButton
                fontSize={`var(--fontSizeM)`}
                onClick={() => navigate('/signup')}
                text={'가입하기'}
              ></TextButton>
              <TextButton
                fontSize={`var(--fontSizeM)`}
                onClick={() => navigate('/main')}
                text={'둘러보기'}
              ></TextButton>
            </div>
            <Img src={user4}></Img>
          </GuideBox>
        </GuideWraper>
      </Box1>
    </Container>
  );
};

export default Landing;

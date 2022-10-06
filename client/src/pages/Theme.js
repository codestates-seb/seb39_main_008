import { useState, useEffect, useRef } from 'react';
import styled from 'styled-components';
import BorderButton from '../components/common/BorderButton';
import { ReactComponent as LogoSvg } from '../assets/logo.svg';
import { setToLocalStorage } from '../lib/localStorage';

const Logo = styled.div`
  cursor: pointer;
  & > svg > path:nth-child(1) {
    fill: ${(props) => props.text1};
  }
  & > svg > path:nth-child(2) {
    fill: ${(props) => props.logoIcon};
  }
`;

const Container = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
`;
const Text1 = styled.p`
  color: ${(props) => props.color};
`;
const Text2 = styled.p`
  color: ${(props) => props.color};
`;
const Text3 = styled.p`
  color: ${(props) => props.color};
`;
const Text4 = styled.p`
  color: ${(props) => props.color};
`;
const Text5 = styled.p`
  color: ${(props) => props.color};
`;
const Text6 = styled.p`
  color: ${(props) => props.color};
`;

const Theme = ({ setHeaderData, setMode, theme, setTheme }) => {
  const backgroundRef = useRef();
  const sidebarRef = useRef();
  const containerRef = useRef();
  const userCardRef = useRef();
  const contentCardRef = useRef();

  const [prevTheme, setPrevTheme] = useState(theme);

  useEffect(() => {
    setHeaderData({
      title: '나만의 DUSKHOUR',
      description: '색다른 일기장을 만들어 보세요 (desktop에서 적용 해주세요)',
    });
  }, []);

  const handleButtonClick = (event, ref) => {
    event.preventDefault();
    ref.current.click();
  };

  const handleColorInput = async (e) => {
    e.preventDefault();
    const id = e.target.id;
    setPrevTheme((prevState) => {
      return {
        ...prevState,
        colors: {
          ...prevState.colors,

          [id]: e.target.value,
        },
      };
    });
  };

  return (
    <Container>
      <DefaultThemeBox>
        <BorderButton
          width={'200px'}
          height={'2.2rem'}
          fontSize={`var(--fontSizeM)`}
          onClick={() => {
            setMode('night');
          }}
          text=" 다크 테마로 설정하기"
        ></BorderButton>
        <BorderButton
          width={'200px'}
          height={'2.2rem'}
          fontSize={`var(--fontSizeM)`}
          onClick={() => {
            setMode('light');
          }}
          text="기본 테마로 설정하기"
        ></BorderButton>
      </DefaultThemeBox>
      <PreviewMain color={prevTheme.colors.border}>
        <PreviewBackground
          className="background"
          color={prevTheme.colors.background}
          onClick={(e) => {
            e.stopPropagation();
            handleButtonClick(e, backgroundRef);
          }}
        >
          <PreviewSidebar
            className="sidebar"
            color={prevTheme.colors.sidebar}
            onClick={(e) => {
              e.stopPropagation();
              handleButtonClick(e, sidebarRef);
            }}
          >
            <h1>사이드바 입니다</h1>
            <Logo
              text1={prevTheme.colors.text1}
              logoIcon={prevTheme.colors.logoIcon}
            >
              <LogoSvg />
            </Logo>

            <Text1 color={prevTheme.colors.text1}>Text1</Text1>
            <Text2 color={prevTheme.colors.text2}>Text2</Text2>
            <Text3 color={prevTheme.colors.text3}>Text3</Text3>
            <Text4 color={prevTheme.colors.text4}>Text4</Text4>
            <Text5 color={prevTheme.colors.text5}>Text5</Text5>
            <Text6 color={prevTheme.colors.text6}>Text6</Text6>
          </PreviewSidebar>
          <PreviewContainer
            color={prevTheme.colors.container}
            onClick={(e) => {
              e.stopPropagation();
              handleButtonClick(e, containerRef);
            }}
          >
            <h1>유저 정보가 담긴 카드입니다</h1>
            <PreviewUserCard
              color={prevTheme.colors.userCard}
              onClick={(e) => {
                e.stopPropagation();
                handleButtonClick(e, userCardRef);
              }}
            >
              <Text1 color={prevTheme.colors.text1}>Text1</Text1>
              <Text2 color={prevTheme.colors.text2}>Text2</Text2>
              <Text3 color={prevTheme.colors.text3}>Text3</Text3>
              <Text4 color={prevTheme.colors.text4}>Text4</Text4>
              <Text5 color={prevTheme.colors.text5}>Text5</Text5>
              <Text6 color={prevTheme.colors.text6}>Text6</Text6>
            </PreviewUserCard>
            <h1>일기장과 일기가 담긴 카드입니다</h1>
            <PreviewContentCard
              color={prevTheme.colors.contentCard}
              onClick={(e) => {
                e.stopPropagation();
                handleButtonClick(e, contentCardRef);
              }}
            >
              <Text1 color={prevTheme.colors.text1}>Text1</Text1>
              <Text2 color={prevTheme.colors.text2}>Text2</Text2>
              <Text3 color={prevTheme.colors.text3}>Text3</Text3>
              <Text4 color={prevTheme.colors.text4}>Text4</Text4>
              <Text5 color={prevTheme.colors.text5}>Text5</Text5>
              <Text6 color={prevTheme.colors.text6}>Text6</Text6>
            </PreviewContentCard>

            <h1>메인 영역 입니다</h1>
            <Text1 color={prevTheme.colors.text1}>Text1</Text1>
            <Text2 color={prevTheme.colors.text2}>Text2</Text2>
            <Text3 color={prevTheme.colors.text3}>Text3</Text3>
            <Text4 color={prevTheme.colors.text4}>Text4</Text4>
            <Text5 color={prevTheme.colors.text5}>Text5</Text5>
            <Text6 color={prevTheme.colors.text6}>Text6</Text6>
          </PreviewContainer>
        </PreviewBackground>
        <input
          className="bgColor"
          ref={backgroundRef}
          onChange={(e) => {
            handleColorInput(e);
          }}
          id="background"
          value={prevTheme.colors.background}
          type="color"
        />
        <input
          className="bgColor"
          ref={containerRef}
          onChange={(e) => {
            handleColorInput(e);
          }}
          id="container"
          value={prevTheme.colors.container}
          type="color"
        />
        <input
          className="bgColor"
          ref={sidebarRef}
          onChange={(e) => {
            handleColorInput(e);
          }}
          id="sidebar"
          value={prevTheme.colors.sidebar}
          type="color"
        />
        <input
          className="bgColor"
          ref={contentCardRef}
          onChange={(e) => {
            handleColorInput(e);
          }}
          id="contentCard"
          value={prevTheme.colors.contentCard}
          type="color"
        />
        <input
          className="bgColor"
          ref={userCardRef}
          onChange={(e) => {
            handleColorInput(e);
          }}
          id="userCard"
          value={prevTheme.colors.userCard}
          type="color"
        />
      </PreviewMain>
      <ColorInputBox>
        <label htmlFor="textColor">
          글자 1
          <input
            className="textColor"
            onChange={(e) => {
              handleColorInput(e);
            }}
            id="text1"
            value={prevTheme.colors.text1}
            type="color"
          />
        </label>
        <label htmlFor="textColor">
          글자 2
          <input
            className="textColor"
            onChange={(e) => {
              handleColorInput(e);
            }}
            id="text2"
            value={prevTheme.colors.text2}
            type="color"
          />
        </label>
        <label htmlFor="textColor">
          글자 3
          <input
            className="textColor"
            onChange={(e) => {
              handleColorInput(e);
            }}
            id="text3"
            value={prevTheme.colors.text3}
            type="color"
          />
        </label>
        <label htmlFor="textColor">
          글자 4
          <input
            className="textColor"
            onChange={(e) => {
              handleColorInput(e);
            }}
            id="text4"
            value={prevTheme.colors.text4}
            type="color"
          />
        </label>
        <label htmlFor="textColor">
          글자 5
          <input
            className="textColor"
            onChange={(e) => {
              handleColorInput(e);
            }}
            id="text5"
            value={prevTheme.colors.text5}
            type="color"
          />
        </label>
        <label htmlFor="textColor">
          글자 6
          <input
            className="textColor"
            onChange={(e) => {
              handleColorInput(e);
            }}
            id="text6"
            value={prevTheme.colors.text6}
            type="color"
          />
        </label>
        <label htmlFor="border">
          구분선
          <input
            className="border"
            onChange={(e) => {
              handleColorInput(e);
            }}
            id="border"
            value={prevTheme.colors.border}
            type="color"
          />
        </label>
        <label htmlFor="logoIcon">
          로고 아이콘
          <input
            className="textColor"
            onChange={(e) => {
              handleColorInput(e);
            }}
            id="logoIcon"
            value={prevTheme.colors.logoIcon}
            type="color"
          />
        </label>
      </ColorInputBox>
      <BorderButton
        width={'200px'}
        height={'2.2rem'}
        fontSize={`var(--fontSizeM)`}
        onClick={() => {
          setToLocalStorage('custom', prevTheme);
          setMode('custom');
          setTheme(prevTheme);
        }}
        text={'색 저장하기'}
      ></BorderButton>
    </Container>
  );
};

export default Theme;

const ColorInputBox = styled.div`
  display: flex;
  label {
    display: flex;
    flex-direction: column;
    margin: var(--spaceS);
    color: ${({ theme }) => theme.colors.green};
    input {
      margin-top: calc(var(--spaceS) / 2);
    }
  }
  margin-bottom: var(--spaceS);
`;
const DefaultThemeBox = styled.div`
  display: flex;
  & > button {
    margin: var(--spaceS);
  }
`;

const PreviewMain = styled.div`
  margin: var(--spaceL) var(--spaceL) 0 var(--spaceL);
  * {
    box-sizing: border-box;
  }
  & div {
    border: 2px solid ${(props) => props.color};
  }
  input[class='bgColor'] {
    display: none;
  }
`;

const PreviewBackground = styled.div`
  width: 720px;
  height: 512px;
  background-color: ${(props) => props.color};
  display: flex;
  justify-content: center;
`;

const PreviewSidebar = styled.div`
  width: 20%;
  background-color: ${(props) => props.color};
  padding: var(--spaceS);
  & > div:nth-child(2) {
    border: none;
    margin: var(--spaceS) 0;
  }
  & > h1 {
    color: ${({ theme }) => theme.colors.green};
    margin-top: var(--spaceS);
    margin-bottom: var(--spaceS);
  }
`;

const PreviewContainer = styled.div`
  width: 60%;
  background-color: ${(props) => props.color};
  padding: 0 var(--spaceM);
  & > h1 {
    color: ${({ theme }) => theme.colors.green};
    margin-top: var(--spaceM);
    margin-bottom: var(--spaceS);
  }
`;

const PreviewContentCard = styled.div`
  padding: var(--spaceS);
  width: 180px;
  height: 140px;
  background-color: ${(props) => props.color};
`;

const PreviewUserCard = styled.div`
  padding: var(--spaceS);
  width: 100px;
  height: 120px;
  background-color: ${(props) => props.color};
`;

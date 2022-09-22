import styled from 'styled-components';
import {
  boxShadow,
  colors,
  space,
  fontSize,
  borderRadius,
} from '../../assets/styles/theme';
import Logo from '../common/Logo';

const Container = styled.div`
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: ${colors.dimGrey};
  z-index: 1000;
`;

const Wraper = styled.div`
  * {
    font-size: ${fontSize.fontSizeL};
    box-sizing: border-box;
    color: ${colors.text2};
  }
  padding: ${space.spaceL};
  padding-top: ${space.spaceS};
  box-shadow: ${boxShadow.shadowM};
  z-index: 1000;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: center;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background: white;
  border-radius: ${borderRadius.borderRadiusM};
  width: 466px;
  height: 249px;

  .target {
    color: ${colors.text2};
  }
  button {
    width: 124px;
    height: 42px;
    color: ${colors.white};
    border-radius: ${borderRadius.borderRadiusS};
    box-shadow: ${boxShadow.shadowM};
  }
  .btns {
    padding: 0 40px;
    width: 100%;
    display: flex;
    justify-content: space-between;
    .true {
      background: ${colors.green};
    }
    .false {
      background: ${colors.red};
    }
  }
`;
const ComfirmModal = ({ message, onComfirm, target }) => {
  return (
    <Container onClick={() => onComfirm(false)}>
      <Wraper onClick={(e) => e.stopPropagation()}>
        <Logo width="115px" />
        <h1 className="target">{target}</h1>
        <h3 className="msg">{message}</h3>
        <div className="btns">
          <button className="true" onClick={() => onComfirm(true)}>
            Yes
          </button>
          <button className="false" onClick={() => onComfirm(false)}>
            Cancel
          </button>
        </div>
      </Wraper>
    </Container>
  );
};

export default ComfirmModal;

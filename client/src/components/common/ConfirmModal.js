import styled from 'styled-components';
import Logo from './Logo';

const Container = styled.div`
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: ${({ theme }) => theme.colors.dimGrey};
  z-index: 1000;
`;

const Wraper = styled.div`
  * {
    font-size: ${({ theme }) => theme.fontSize.fontSizeL};
    box-sizing: border-box;
    color: ${({ theme }) => theme.colors.text2};
  }

  padding: ${({ theme }) => theme.space.spaceL};
  padding-top: ${({ theme }) => theme.space.spaceS};
  box-shadow: ${({ theme }) => theme.boxShadow.shadowM};
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
  border-radius: ${({ theme }) => theme.borderRadius.borderRadiusM};
  width: 466px;
  height: 249px;

  .target {
    color: ${({ theme }) => theme.colors.text2};
  }

  button {
    width: 124px;
    height: 42px;
    color: ${({ theme }) => theme.colors.white};
    border-radius: ${({ theme }) => theme.borderRadius.borderRadiusS};
    box-shadow: ${({ theme }) => theme.boxShadow.shadowM};
  }
  .btns {
    padding: 0 40px;
    width: 100%;
    display: flex;
    justify-content: space-between;

    .true {
      background: ${({ theme }) => theme.colors.green};
    }

    .false {
      background: ${({ theme }) => theme.colors.red};
    }
  }
`;
const ConfirmModal = ({ message, onComfirm, target }) => {
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

export default ConfirmModal;

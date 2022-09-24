import styled from 'styled-components';
import { colors, fontSize, space } from '../../assets/styles/theme';
const InputBox = styled.div`
  width: 100%;
  height: 80px;
  position: relative;
  background-color: transparent;
  input {
    color: ${colors.text2};
    background: transparent;
    border: none;
    border-bottom: solid 1px ${colors.grey};
    padding: 20px 0px 5px 10px;
    width: 100%;
  }
  input::placeholder {
    font-size: ${fontSize.fontSizeS};
    text-align: right;
  }
  input:placeholder-shown + label {
    color: ${colors.text2};
    top: 1.2rem;
  }
  input:focus + label,
  label {
    color: ${colors.text4};
    font-size: 0.9rem;
    pointer-events: none;
    position: absolute;
    left: 0px;
    top: 0px;
    transition: all 0.2s ease;
    -webkit-transition: all 0.2s ease;
    -moz-transition: all 0.2s ease;
    -o-transition: all 0.2s ease;
  }
  input:focus,
  input:not(:placeholder-shown) {
    border-bottom: solid 1px ${colors.black};
    outline: none;
    background-color: none;
  }

  .error {
    margin-top: calc(${space.spaceS} / 3);
    color: ${colors.red};
    font-size: ${fontSize.fontSizeS};
  }
`;
const Input = ({ option, ...props }) => {
  return (
    <InputBox>
      <input
        id={option.id}
        type={option.type}
        name={option.id}
        placeholder={option.placeholder}
        {...props.register(option.id)}
      />
      {props.errors[option.id] && (
        <p className="error">{props.errors[option.id].message}</p>
      )}
      <label htmlFor={option.id}>{option.label}</label>
    </InputBox>
  );
};

export default Input;

import styled from 'styled-components';
const InputBox = styled.div`
  width: 100%;
  height: 80px;
  position: relative;
  background-color: transparent;

  input {
    color: ${({ theme }) => theme.colors.text2};
    background: transparent;
    border: none;
    border-bottom: solid 1px ${({ theme }) => theme.colors.border};
    padding: 20px 0px 5px 10px;
    width: 100%;
  }

  input::placeholder {
    font-size: var(--fontSizeS);
    text-align: right;
  }

  input:placeholder-shown + label {
    color: ${({ theme }) => theme.colors.text2};
    top: 1.2rem;
  }

  input:focus + label,
  label {
    color: ${({ theme }) => theme.colors.text4};
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
    border-bottom: solid 1px ${({ theme }) => theme.colors.text1};
    outline: none;
    background-color: none;
  }

  .error {
    margin-top: calc(var(--spaceS) / 3);
    color: ${({ theme }) => theme.colors.red};
    font-size: var(--fontSizeS);
  }
`;
const SiginupInput = ({ option, ...props }) => {
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

export default SiginupInput;

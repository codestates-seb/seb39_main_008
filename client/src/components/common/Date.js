import styled from 'styled-components';

const DateText = styled.p`
  text-align: end;
  font-size: ${(props) =>
    props.fontsize ? props.fontSize : `var(--fontSizeM)`};
`;

const Date = ({ date, ...props }) => {
  let year = date.slice(0, 4);
  let month = Number(date.slice(5, 7));
  let day = Number(date.slice(8, 11));
  const dateText = `${year}년 ${month}월 ${day}일`;

  return <DateText fontsize={props.fontsize}>{dateText}</DateText>;
};

export default Date;
